package ru.filippov.springTree.treeOfSolution;

import org.springframework.stereotype.Service;

import java.util.*;


public class Node implements Cloneable{
    private Node parent;
    private String label;
    private Set<String> attributes;
    private String attributeName;
    private List<Node> children;
    private Set<Integer> examples;
    private int example;
    private short level;
    private float E0;
    private float E;
    private short countOfAttributes;
    private float lastIG;
    private short depth;
    private int totalNodes;
    private int id;
    private int lastID;


    public Node(){}
    public Node(String attributeName, String label, int example, short countOfAttributes) {
        this.attributeName = attributeName;
        this.label = label;
        this.example = example;
        this.examples = new HashSet<>();
        this.examples.add(example);
        this.countOfAttributes = countOfAttributes;
        this.attributes = new HashSet<>();
        this.attributes.add(attributeName);
        this.children = new ArrayList<>();
        this.lastIG = -1;
        this.totalNodes = 0;
        this.lastID = 0;
        this.id = 0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Node clone = new Node(this.attributeName, this.label, this.example, this.countOfAttributes);

        clone.parent = null;
        clone.label = this.label;
        clone.attributes = new HashSet<>(this.attributes);
        clone.attributeName = this.attributeName;
        clone.id = this.id;
        clone.lastID = this.lastID;
        if(!this.children.isEmpty()){
            ArrayList<Node> copiesOfChildren = new ArrayList<>();
            for(Node child: children){
                copiesOfChildren.add((Node) child.clone(clone));
            }
            clone.children = copiesOfChildren;
        }
        clone.examples = new HashSet<>(this.examples);
        clone.level = this.level;
        clone.E0 = 0;
        clone.E = 0;
        clone.countOfAttributes = this.countOfAttributes;
        clone.lastIG = this.lastIG;
        clone.depth = this.depth;
        clone.totalNodes = this.totalNodes;
        return clone;
    }

    protected Object clone(Node parent) throws CloneNotSupportedException {
        Node clone = new Node(this.attributeName, this.label, this.example, this.countOfAttributes);

        clone.parent = parent;
        clone.label = this.label;
        clone.attributes = new HashSet<>(this.attributes);
        clone.attributeName = this.attributeName;
        clone.id = this.id;
        if(!this.children.isEmpty()){
            ArrayList<Node> copiesOfChildren = new ArrayList<>();
            for(Node child: children){
                copiesOfChildren.add((Node) child.clone(clone));
            }
            clone.children = copiesOfChildren;
        }
        clone.examples = new HashSet<>(this.examples);
        clone.level = this.level;
        clone.E0 = 0;
        clone.E = 0;
        clone.countOfAttributes = this.countOfAttributes;
        clone.lastIG = this.lastIG;
        clone.depth = this.depth;
        return clone;
    }

    void getAllNodesOnLevel(int level, List<Node> nodes){
        for(Node child:this.children){
            if(child.level < level) child.getAllNodesOnLevel(level, nodes);
            if(child.level == level) nodes.add(child);
        }
    }

    public float getIGOfLastAdding(){
        return calculateIGForLevel(this.depth);
    }

    public float calculateIGForLevel(short level){
        ArrayList<Node> nodesOnLevel = new ArrayList<>();
        this.getAllNodesOnLevel((short) (level-1), nodesOnLevel);
        this.E0 = 0;
        this.lastIG = 0;
        float IG = 0;
        for(Node parent : nodesOnLevel){
            float pForE0 = (float)parent.examples.size()/this.examples.size();
            parent.E0 = (float) -(pForE0*Math.log10(pForE0));
            float totalE = 0;
            for(Node child : parent.children){
                float p = (float)child.examples.size()/parent.examples.size();
                child.E += -(p*Math.log10(p));
                totalE += child.E;

            }
            IG += pForE0 * totalE;
        }
        float totalE0 = 0;
        for(Node parent: nodesOnLevel){
            totalE0 += parent.E0;
        }
        IG = totalE0 - IG;
        this.lastIG = IG;

        return this.lastIG;
    }




    public double log(float x, int base) {
        return (Math.log(x) / Math.log(base));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return example == node.example &&
                level == node.level &&
                Objects.equals(label, node.label) &&
                Objects.equals(attributeName, node.attributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, attributeName, example, level);
    }

    public void setDepth(short currentMaxLevel){
        if(this.depth < currentMaxLevel) this.depth = currentMaxLevel;
    }

    public void push(Node newNode){
        try {
            Node copyNewNode = (Node) newNode.clone();

            //Для головы
            if(this.parent == null && this.children.isEmpty() && this.equals(newNode)){
                try {

                    Node copyNode = (Node) this.clone();
                    copyNode.parent = this;
                    this.level = 0;
                    copyNode.level = 1;
                    this.id = this.lastID++;
                    this.setDepth((short) 1);
                    this.label = "HEAD";

                    copyNode.id = this.lastID++;
                    this.children.add(copyNode);
                    this.totalNodes+=2;
                    return;
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            //Добавочные узлы к корню
            if(this.parent == null && !this.children.isEmpty() && !this.examples.contains(copyNewNode.example)){
                if(this.children.get(0).attributeName.equals(copyNewNode.attributeName)) {
                    for (Node child : children) {
                        if (child.label.equals(copyNewNode.label)) {
                            child.examples.add(copyNewNode.example);
                            child.parent.examples.add(copyNewNode.example);
                            return;
                        }
                    }
                    copyNewNode.parent = this;
                    copyNewNode.parent.examples.add(copyNewNode.example);
                    copyNewNode.level = (short) (copyNewNode.parent.level+1);
                    this.setDepth(copyNewNode.level);
                    this.children.add(copyNewNode);
                    copyNewNode.id = this.lastID++;
                    this.totalNodes++;
                    return;
                }
            }
            //Пошло добавление
            //Если аттрибут уже есть в дереве, то нужно добавить пример в нужный аттрибут и в нужный класс
            if(attributes.contains(copyNewNode.attributeName)){
                Node node = this.findParentForNewNode(copyNewNode);
                if(node != null){
                    //Если такой класс уже существует, то просто добавляем пример в узел, и дополняем родительские примеры
                    if(!node.children.isEmpty()) {
                        for (Node child : node.children) {
                            if (child.label.equals(copyNewNode.label)) {
                                copyNewNode.level = child.level;
                                this.setDepth(copyNewNode.level);
                                child.examples.add(copyNewNode.example);
                                return;
                            }
                        }
                    }
                    //___________________________________________________________________________
                    //Если узла с таким классом нет, то нужно добавить класс в качестве дочернего
                    copyNewNode.parent = node;
                    copyNewNode.level = (short) (copyNewNode.parent.level+1);
                    this.setDepth(copyNewNode.level);
                    node.children.add(copyNewNode);
                    copyNewNode.id = this.lastID++;
                    this.totalNodes++;
                    copyNewNode.addAttributesToParrents(copyNewNode.attributeName);
                    return;
                    //___________________________________________________________________________
                }
            }
            else {
                Node node = this.findParentForNewNode(copyNewNode);
                copyNewNode.parent = node;
                copyNewNode.level = (short) (node.level+1);
                this.setDepth(copyNewNode.level);
                node.children.add(copyNewNode);
                copyNewNode.id = this.lastID++;
                this.totalNodes++;
                copyNewNode.addAttributesToParrents(copyNewNode.attributeName);

            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    Node findParentForNewNode(Node newNode){
        if(this.children.size() > 0){
            for(Node child : this.children){
                if(child.examples.contains(newNode.example)){
                    if(child.attributes.size() == 2 && child.attributes.contains(newNode.attributeName)) return child;
                    /*if(child.attributes.size() == 2 && !child.attributes.contains(newNode.attributeName)) return child.findParentForNewNode(newNode);
                    if(child.attributes.contains(newNode.attributeName)) return child.findParentForNewNode(newNode);*/
                    return child.findParentForNewNode(newNode);
                }
            }
        }
        return this;
    }

    public float getLastIG() {
        return lastIG;
    }

    void addAttributesToParrents(String attributeName){
        this.attributes.add(attributeName);
        if(this.parent != null) {
            this.parent.addAttributesToParrents(attributeName);
        }
    }

    void deleteAttributeFromTree(String attributeName){
        this.attributes.remove(attributeName);
        if(this.parent != null){
            this.parent.deleteAttributeFromTree(attributeName);
        }
    }

    public void deleteLastLevel(String nameOfLastLevel) {
        List<Node> parents = new ArrayList<>();
        getAllNodesOnLevel(depth-1, parents);
        for(Node parent : parents){
            this.totalNodes -= parent.children.size();
            this.lastID -= parent.children.size();
            parent.deleteAttributeFromTree(nameOfLastLevel);
            parent.children = new ArrayList<>(parent.children.size());
        }
    }

    public String getAttributeName() {
        return attributeName;
    }

    private void putChildrenToAdjacencyMatrix(Node parent, Node[][] matrix){
        if(!parent.children.isEmpty()){
            for (Node child: parent.children) {
                matrix[parent.id][child.id] = child;
                //matrix[child.id][parent.id] = parent;
                matrix[child.id][child.id] = parent;
                putChildrenToAdjacencyMatrix(child, matrix);
            }
        }

    }

    public Node[][] convertTreeToAdjacencyMatrix(){
        Node[][] matrix = new Node[this.totalNodes][this.totalNodes];
        this.putChildrenToAdjacencyMatrix(this, matrix);
        return matrix;
    }


    public String getLabel() {
        return label;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Set<Integer> getExamples() {
        return examples;
    }

    public short getLevel() {
        return level;
    }

    public int getID() {
        return this.id;
    }
}