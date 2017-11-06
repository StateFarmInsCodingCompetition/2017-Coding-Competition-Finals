package sf.codingcomp.blocks;

public class Claim {

    private int atFault;
    private int recipient;
    private double amountOfDamage;

    public int getAtFault() {
        return atFault;
    }

    public void setAtFault(int atFault) {
        this.atFault = atFault;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public double getAmountOfDamage() {
        return amountOfDamage;
    }

    public void setAmountOfDamage(double amountOfDamage) {
        this.amountOfDamage = amountOfDamage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(amountOfDamage);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + atFault;
        result = prime * result + recipient;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Claim other = (Claim) obj;
        if (Double.doubleToLongBits(amountOfDamage) != Double.doubleToLongBits(other.amountOfDamage))
            return false;
        if (atFault != other.atFault)
            return false;
        if (recipient != other.recipient)
            return false;
        return true;
    }

}
