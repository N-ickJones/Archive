package StarWarsFxml;

public class Resource {

    private int ER, CR, CD, DR, FL, HR, MA, PE, OQ, SR, UT, quantity;
    private double rank, time;

    //private char  ;
    private String planet, type, name, schematic, profession, property;


    public Resource(){
        this.planet = "";
        this.name = "";
        this.type = "";
        this.quantity = 0;
        this.ER = 0;
        this.CR = 0;
        this.CD = 0;
        this.DR = 0;
        this.FL = 0;
        this.HR = 0;
        this.MA = 0;
        this.PE = 0;
        this.OQ = 0;
        this.SR = 0;
        this.UT = 0;
        this.rank = 0.0;
        this.time = 0.0;
        this.schematic = "";
        this.profession = "";
        this.property = "";
    }

    public Resource(String planet, String name) {

        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.planet = planet;
        this.ER = ER;
        this.CR = CR;
        this.CD = CD;
        this.DR = DR;
        this.FL = FL;
        this.HR = HR;
        this.MA = MA;
        this.PE = PE;
        this.OQ = OQ;
        this.SR = SR;
        this.UT = UT;
        this.schematic = schematic;
        this.profession = profession;
        this.property = property;
        this.rank = rank;
        this.time = time;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setPlanet(String planet)
    {
        this.planet = planet;
    }

    public void setER(int ER)
    {
        this.ER = ER;
    }

    public void setCR(int CR)
    {
        this.CR = CR;
    }

    public void setCD(int CD)
    {
        this.CD = CD;
    }

    public void setDR(int DR)
    {
        this.DR = DR;
    }

    public void setFL(int FL)
    {
        this.FL = FL;
    }

    public void setHR(int HR)
    {
        this.HR = HR;
    }

    public void setMA(int MA)
    {
        this.MA = MA;
    }

    public void setPE(int PE)
    {
        this.PE = PE;
    }

    public void setOQ(int OQ)
    {
        this.OQ = OQ;
    }

    public void setSR(int SR)
    {
        this.SR = SR;
    }

    public void setUT(int UT)
    {
        this.UT = UT;
    }

    public void setSchematic(String schematic)
    {
        this.schematic = schematic;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }


    public void setRank(double rank)
    {
        this.rank = rank;
    }

    public void setTime(double time)
    {
        this.time = time;
    }





    public String getPlanet()
    {
        return planet;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getEr()
    {
        return ER;
    }

    public int getCr()
    {
        return CR;
    }

    public int getCd()
    {
        return CD;
    }

    public int getDr()
    {
        return DR;
    }

    public int getFl()
    {
        return FL;
    }

    public int getHr()
    {
        return HR;
    }

    public int getMa()
    {
        return MA;
    }

    public int getPe()
    {
        return PE;
    }

    public int getOq()
    {
        return OQ;
    }

    public int getSr()
    {
        return SR;
    }

    public int getUt()
    {
        return UT;
    }

    public double getRank()
    {
        return rank;
    }

    public double getTime()
    {
        return time;
    }

    public String getSchemnatic()
    {
        return schematic;
    }

    public String getProfession()
    {
        return profession;
    }

    public String getProperty()
    {
        return property;
    }

}
