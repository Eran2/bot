public class MessageAnalysis {
    private String txt;
    private String[] options=new String[5];
    int Status;
    MessageAnalysis(){
    Status=1;
    };

    private void setWatches(){
        options[1]="1.Breitling";
        options[2]="2.rado";
        options[3]="3.Tissot";
        options[4]="4.Rolex";
    }

    private void setShoes(){
        options[1]="1.Nike";
        options[2]="2.SteveMadden";
        options[3]="3.Adidas";
        options[4]="4.Sakuni";
    }
    private void setBracelet(){
        options[1]="1.Bvlgary";
        options[2]="2.Masho";
        options[3]="3.Masho";
        options[4]="4.Masho";
    }
    private void setBags(){
        options[1]="1.Guess";
        options[2]="2.Guess";
        options[3]="3.Guess";
        options[4]="4.Guess";
    }
    private void setOptions(){
        options[1]="1.שעונים";
        options[2]="2.נעליים";
        options[3]="3.תיקים";
        options[4]="4.צמידים";

    }
    public void setTxt(String txt) {
        this.txt = txt;
    }
    public String[] MsgToShow(){
        System.out.println(txt);
        if(Status==3) {
            setOptions();
            Status=1;
        }
        if(Status==2) {
            if (txt.equals("1"))
            {
                setWatches();
                Status=3;
            }
            if (txt.equals("2"))
            {
                setShoes();
                Status=3;
            }
            if (txt.equals("3"))
            {
                setBags();
                Status=3;
            }
            if (txt.equals("4"))
            {
                setBracelet();
                Status=3;
            }
        }

        if(Status==1) {
            setOptions();
            Status=2;
        }
        return options;

    }
}
