public class Data extends TreeNode {
        String[] data;
        
        public Data(String[] data) {
            this.data = data;
        }
        
        public Data getData() {
            return this;
        }
        
        public String getDate() {
            return data[0];
        }
        
        public String getState() {
            return data[1];
        }
        
}