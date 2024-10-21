package org.hbrs.se1.ws24.uebung3.view;

import java.util.List;

import org.hbrs.se1.ws24.uebung3.model.Member;

public class MemberView {

    public void dump(List<Member> memberList) {
        for (Member member : memberList) {
            System.out.println(member.toString());
        }
    }
}
