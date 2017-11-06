package sf.codingcomp.blocks;

import java.util.Collection;

public class Blockchain {

    private Collection<Block<?>> chain;

    public Blockchain() {
        super();
        // TODO initialize chain to whatever collection type you want
        Block<?> genesisBlock = new Block<>(null);
        genesisBlock.setHash(VerificationHashFactory.buildVerificationHash(genesisBlock));
        // TODO add the genesis block as the first block in the chain
    }

    public void addBlock(Block<?> block) {
    }

    public boolean verify() {
        return false;
    }

}
