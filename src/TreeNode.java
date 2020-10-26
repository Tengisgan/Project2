public abstract class TreeNode {

    TreeNode left;
    TreeNode right;
    
    public TreeNode(Flyweight fly) {
        left = fly;
        right = fly;
        
    }
    
    public abstract Data getData();
//      public void sortTreeNode() {
//          for (int i = 1; i < this.data.size(); i++) {
//              Data current = this.data.get(i);
//              for (int j = 0; j < i; j++) {
//                  if (current.data[0].compareTo(this.data.get(j).data[0]) > 0) {
//                      Data placeHolder = this.data.get(j);
//                      this.data.set(j, current);
//                      this.data.set(i, placeHolder);
//                  }
//              }
//          }
//      }
}