package org.hbrs.se1.ws24.uebung3.client;

import org.hbrs.se1.ws24.uebung3.control.Container;
import org.hbrs.se1.ws24.uebung3.exceptions.ContainerException;
import org.hbrs.se1.ws24.uebung3.model.ConcreteMember;
import org.hbrs.se1.ws24.uebung3.view.MemberView;

public class Client {
    public static void main(String[] args) {
        Container container = Container.getInstance();

        try {
            container.addMember(new ConcreteMember(1));
            container.addMember(new ConcreteMember(2));
            container.addMember(new ConcreteMember(3));

            MemberView memberView = new MemberView();
            memberView.dump(container.getCurrentList());

        } catch (ContainerException e) {
            System.err.println("Fehler beim Hinzufügen eines Members: " + e.getMessage());
        }
    }
}

