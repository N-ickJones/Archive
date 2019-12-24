public class Sudoku {

    private int column1, column2, column3, column4, column5, column6, column7, column8, column9;

    public Sudoku(){

        this.column1 = 0;
        this.column2 = 0;
        this.column3 = 0;
        this.column4 = 0;
        this.column5 = 0;
        this.column6 = 0;
        this.column7 = 0;
        this.column8 = 0;
        this.column9 = 0;
    }

    public Sudoku(int col1, int col2, int col3, int col4,
                  int col5, int col6, int col7, int col8, int col9) {

        this.column1 = col1;
        this.column2 = col2;
        this.column3 = col3;
        this.column4 = col4;
        this.column5 = col5;
        this.column6 = col6;
        this.column7 = col7;
        this.column8 = col8;
        this.column9 = col9;

    }

    public void setColumn1(int column1) {
        this.column1 = column1;
    }

    public void setColumn2(int column2) {
        this.column2 = column2;
    }

    public void setColumn3(int column3) {
        this.column3 = column3;
    }

    public void setColumn4(int column4) {
        this.column4 = column4;
    }

    public void setColumn5(int column5) {
        this.column5 = column5;
    }

    public void setColumn6(int column6) {
        this.column6 = column6;
    }

    public void setColumn7(int column7) {
        this.column7 = column7;
    }

    public void setColumn8(int column8) {
        this.column8 = column8;
    }

    public void setColumn9(int column9) {
        this.column9 = column9;
    }

    public int getColumn1(){
        return column1;
    }

    public int getColumn2(){
        return column2;
    }


    public int getColumn3(){
        return column3;
    }


    public int getColumn4(){
        return column4;
    }

    public int getColumn5(){
        return column5;
    }

    public int getColumn6(){
        return column6;
    }

    public int getColumn7(){
        return column7;
    }

    public int getColumn8(){
        return column8;
    }

    public int getColumn9(){
        return column9;
    }

}