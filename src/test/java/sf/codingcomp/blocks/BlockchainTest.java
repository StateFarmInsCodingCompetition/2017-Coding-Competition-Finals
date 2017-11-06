package sf.codingcomp.blocks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class BlockchainTest {

    Blockchain chain = new Blockchain();

    @Test
    public void testValidChain() {
        Claim claim1 = new Claim();
        claim1.setAmountOfDamage(1000d);
        claim1.setRecipient(1);
        claim1.setAtFault(2);
        chain.addBlock(new ClaimBlock(claim1));
        Claim claim2 = new Claim();
        claim2.setAmountOfDamage(1100d);
        claim2.setRecipient(3);
        claim2.setAtFault(4);
        chain.addBlock(new ClaimBlock(claim2));
        Payment payment1 = new Payment(); // payment for claim1
        payment1.setAmount(1000d);
        payment1.setPayer(2);
        payment1.setPayee(1);
        chain.addBlock(new PaymentBlock(payment1));
        Payment payment2 = new Payment(); // payment for claim1
        payment2.setAmount(1100d);
        payment2.setPayer(4);
        payment2.setPayee(3);
        chain.addBlock(new PaymentBlock(payment2));
        assertTrue(chain.verify());
    }
    
    @Test
    public void testInValidChainBecauseOfCurrentHash() {
        Claim claim1 = new Claim();
        claim1.setAmountOfDamage(1000d);
        claim1.setRecipient(1);
        claim1.setAtFault(2);
        chain.addBlock(new ClaimBlock(claim1));
        Claim claim2 = new Claim();
        claim2.setAmountOfDamage(1100d);
        claim2.setRecipient(3);
        claim2.setAtFault(4);
        chain.addBlock(new ClaimBlock(claim2));
        Payment payment1 = new Payment(); // payment for claim1
        payment1.setAmount(1000d);
        payment1.setPayer(2);
        payment1.setPayee(1);
        chain.addBlock(new PaymentBlock(payment1));
        Payment payment2 = new Payment(); // payment for claim1
        payment2.setAmount(1100d);
        payment2.setPayer(4);
        payment2.setPayee(3);
        PaymentBlock block = new PaymentBlock(payment2);
        chain.addBlock(block);
        assertTrue(chain.verify());
        block.setHash("some incorrect hash");
        assertFalse(chain.verify());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBadBlockFailsBecuaseOfPrevHashNotMatching() {
        Claim claim1 = new Claim();
        claim1.setAmountOfDamage(1000d);
        claim1.setRecipient(1);
        claim1.setAtFault(2);
        chain.addBlock(new ClaimBlock(claim1));
        Claim claim2 = new Claim();
        claim2.setAmountOfDamage(1100d);
        claim2.setRecipient(3);
        claim2.setAtFault(4);
        chain.addBlock(new ClaimBlock(claim2));
        Payment payment1 = new Payment(); // payment for claim1
        payment1.setAmount(1000d);
        payment1.setPayer(2);
        payment1.setPayee(1);
        chain.addBlock(new PaymentBlock(payment1));
        Payment payment2 = new Payment(); // payment for claim1
        payment2.setAmount(1100d);
        payment2.setPayer(4);
        payment2.setPayee(3);
        chain.addBlock(new PaymentBlock(payment2));
        assertTrue(chain.verify());

        // Maliciously insert block

        Block<Object> badBlock = new Block<Object>(new Object()) {
            @Override
            public boolean validate() {
                return true;
            }
        };

        LinkedList<Block<?>> badChain = (LinkedList<Block<?>>) ReflectionTestUtils.getField(chain, "chain");
        badBlock.setHash(VerificationHashFactory.buildVerificationHash(badBlock));

        badChain.add(1, badBlock);

        assertFalse(chain.verify());
    }

}
