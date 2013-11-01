/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovm;

/**
 *
 * @author shatabdi
 */
public class Product {
    String pName,pCategory;
    int pId;
    double qty;
    double price;
    String desc;
    public String getPName()
    {
        return pName;
    }
    public void setPNname(String pName)
    {
        this.pName=pName;
        
    }
    public String getPCategory()
    {
        return pCategory;
    }
    public void setPCategory(String pCategory)
    {
        this.pCategory=pCategory;
    }
    public int getPId()
    {
        return pId;
    }
    public void setPId(int pId)
    {
        this.pId=pId;
    }
    public double getQty()
    {
        return qty;
    }
    public void setQty(double qty)
    {
        this.qty=qty;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price=price;
    }
    public String getDesc()
    {
        return desc;
    }
    public void setPDesc(String desc)
    {
        this.desc=desc;
        
    }
    public boolean equals(Object o)
    {
        if(o instanceof Product)
        {    
            return (((Product)o).pId==(pId))
            ;
        }
        {
        
        return false;
        }
    }
}
