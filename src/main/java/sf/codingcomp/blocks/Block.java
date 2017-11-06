package sf.codingcomp.blocks;

public class Block<T> {

    private T data;
    private String hash;
    private String previousHash;

    public Block(T data) {
        super();
        setData(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public boolean validate() {
        return getHash() != null && getHash().equals(VerificationHashFactory.buildVerificationHash(this));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((previousHash == null) ? 0 : previousHash.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Block other = (Block) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (previousHash == null) {
            if (other.previousHash != null)
                return false;
        } else if (!previousHash.equals(other.previousHash))
            return false;
        return true;
    }

}
