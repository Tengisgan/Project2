public class Data extends TreeNode {
        String[] data;
        
        public Data(Flyweight fly, String[] data) {
            super(fly);
            this.data = data;
        }
        
        public Data getData() {
            return this;
        }
        
        public String[] getArray() {
            return data;
        }
        
        public String getDate() {
            return data[0];
        }
        
        public String getState() {
            return data[1];
        }
        
        public String getPositive() {
            return data[2];
        }
        
        public String getGrade() {
            return data[8];
        }
        
        public boolean existingDataGradeIsLessThan(String[] existingData, String[] newData) {
            return getNumericalGrade(existingData[8]) < getNumericalGrade(newData[8]);
        }
        
        /**
         * Converts all the String qualityGrades to integer 
         * values so that they can be compared to properly
         * @param qualityGrade : String qualityGrade to be converted to int
         * @return : int version of the quality grade
         */
        private int getNumericalGrade(String qualityGrade) {
            int grade = (700 - ((int) qualityGrade.charAt(0) * 10));
            return qualityGrade.length() == 1 ? grade : grade + 
                (44 - ((int) qualityGrade.charAt(1)));
        }
        
        public boolean gradeIsBetterOrEqual(String grade) {
        	return getNumericalGrade(getGrade()) >= getNumericalGrade(grade) ?
        			true : false;
        }
        
        public void printData() {
        	System.out.print(data[1] + " " + data[0]);
    		for (int index = 2; index < data.length; index++) {
    			if (data[index].equals("")) {
    				System.out.print(" 0");
    			}
    			else {
    				System.out.print(" " + data[index]);
    			}
    		}
    		System.out.print("\n");
    	}
}