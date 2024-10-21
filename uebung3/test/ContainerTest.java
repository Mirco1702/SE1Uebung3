package org.hbrs.se1.ws24.uebung3.test;

import org.hbrs.se1.ws24.uebung3.model.Member;
import org.hbrs.se1.ws24.uebung3.control.Container;
import org.hbrs.se1.ws24.uebung3.exceptions.ContainerException;
import org.hbrs.se1.ws24.uebung3.exceptions.PersistenceException;
import org.hbrs.se1.ws24.uebung3.model.ConcreteMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    private Container container;

    @BeforeEach
    public void setUp() {
        container = Container.getInstance();
        container.getCurrentList().clear(); 
    }

    @Test
    public void testAddMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        container.addMember(member1);
        assertEquals(1, container.size());
    }

    @Test
    public void testDeleteMember() throws ContainerException {
        Member member = new ConcreteMember(1);
        container.addMember(member);
        assertEquals("Das Member-Objekt mit der ID 1 wurde erfolgreich gelöscht", container.deleteMember(1));
        assertEquals(0, container.size());
    }

    @Test
    public void testStoreWithoutStrategy() {
        assertThrows(PersistenceException.class, () -> {
            container.store(); 
        });
    }

    @Test
    public void testRoundTrip() throws ContainerException, PersistenceException {
        Member member = new ConcreteMember(1);
        container.addMember(member);
        assertEquals(1, container.size());

        container.store(); 
        container.deleteMember(1);
        assertEquals(0, container.size());

        container.load(); 
        assertEquals(1, container.size());
    }
}
