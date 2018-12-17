package ru.filippov.springTree.treeOfSolution;



import org.springframework.stereotype.Service;
import ru.filippov.springTree.utils.DataFactory;

import java.util.ArrayList;
import java.util.List;


public class TreeBuilder extends Thread{
    int status = 0;




    Node tree;
    String[][] data;
    private float statusIncrementor = -1;
    private String responseVariable;

    public TreeBuilder() {
        this.tree = null;
    }
    public TreeBuilder(String[][] data, String responseVariable) {
        this.data = data;
        this.responseVariable = responseVariable;
        statusIncrementor = 100.0f / ((this.data[0].length * (this.data[0].length-1) + calculateFactorial(this.data[0].length-2)));
        this.tree = null;
    }

    public Node getTree() {
        return tree;
    }

    void updateStatus(){
        this.status += statusIncrementor;
    }

    private int calculateFactorial(int n){
        int result = 1;
        for (int i = 1; i <=n; i ++){
            result = result*i;
        }
        return result;
    }


    @Override
    public void run() {
        Node[][] nodes = DataFactory.convertDataToNode(data);
        short countOfAttributes = (short) data[0].length;
        int countOfTrees = (countOfAttributes) * (countOfAttributes - 1);
        //if(tree == null) tree = nodes[0][3];
        Node currentTree = null;
        short indexOfResponseVariable = 0;
        short firstIndex = 0;
        short secondIndex = 1;
        short bestColumn1 = -1;
        short bestColumn2 = -1;
        try {
            for (int k = 0; k < countOfTrees; k++) {

                if (secondIndex >= countOfAttributes) {
                    secondIndex = 0;
                    firstIndex++;
                }
                if(nodes[0][firstIndex].getAttributeName().equals(this.responseVariable)) {
                    indexOfResponseVariable = firstIndex;
                    currentTree = null;
                    secondIndex++;
                    if (firstIndex == secondIndex) secondIndex++;
                    updateStatus();
                    continue;
                }
                for (int i = 0; i < nodes.length; i++) {
                    if (currentTree == null) currentTree = (Node) nodes[i][firstIndex].clone();
                    currentTree.push(nodes[i][firstIndex]);

                }

                for (int i = 0; i < nodes.length; i++) {
                    currentTree.push(nodes[i][secondIndex]);

                }

                if (this.tree == null) {
                    this.tree = currentTree;
                    this.tree.getIGOfLastAdding();
                    bestColumn1 = firstIndex;
                    bestColumn2 = secondIndex;
                } else {
                    if (this.tree.getLastIG() < currentTree.getIGOfLastAdding()) {
                        this.tree = currentTree;
                        bestColumn1 = firstIndex;
                        bestColumn2 = secondIndex;
                    }
                }
                currentTree = null;
                secondIndex++;
                if (firstIndex == secondIndex) secondIndex++;
                updateStatus();

            }


            List<Short> allowedColumns = new ArrayList<>(nodes[0].length-1);
            for (short i = 0; i < countOfAttributes; i++) {
                if(i == bestColumn1 || i == bestColumn2 || i == indexOfResponseVariable) continue;
                allowedColumns.add(i);
            }


            /*for (int i = 0; i < nodes.length; i++) {
                tree.push(nodes[i][allowedColumns.get(0)]);
            }
            tree.getIGOfLastAdding();
            int a = 0;*/

            //currentTree = (Node) this.tree.clone();

            while (!allowedColumns.isEmpty()){
                currentTree = this.tree;
                boolean secondStage = true;
                for (Short column: allowedColumns) {
                    for (int i = 0; i < nodes.length; i++) {
                        currentTree.push(nodes[i][column]);
                    }
                    updateStatus();
                    if(secondStage) {
                        this.tree = (Node) currentTree.clone();
                        this.tree.getIGOfLastAdding();
                        secondStage = false;
                    }
                    if(this.tree.getLastIG() < currentTree.getIGOfLastAdding()){
                        this.tree = currentTree;
                        currentTree = (Node) this.tree.clone();
                        bestColumn2 = column;
                    }
                    if (allowedColumns.size() == 1){
                        this.tree = currentTree;
                        bestColumn2 = column;

                        break;
                    }
                    currentTree.deleteLastLevel(nodes[0][column].getAttributeName());
                }
                allowedColumns.remove(new Short(bestColumn2));
            }
            for (int i = 0; i < nodes.length; i++) {
                currentTree.push(nodes[i][indexOfResponseVariable]);
            }
            this.status = 100;
        } catch(CloneNotSupportedException ex){
            System.out.println("Произошла ошибка в методе TreeBuilder.run");
        ex.printStackTrace();
        return;
        }
}

    public float getStatus() {
        return status;
    }

    public Node[][] getAdjacencyMatrixOfTree(){
        return this.tree.convertTreeToAdjacencyMatrix();
    }
}
