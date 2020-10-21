public class Data extends TreeNode {
        String[] data;
        
        public Data(Flyweight fly, String[] data) {
            super(fly);
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
        
        public boolean compareGrades(TreeNode existing) {
        	return false;
        }
}