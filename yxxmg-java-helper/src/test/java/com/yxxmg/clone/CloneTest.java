package com.yxxmg.clone;

import java.util.Collections;

import org.apache.commons.lang3.SerializationUtils;

import junit.framework.TestCase;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/21
 */
public class CloneTest extends TestCase {

    public void test() {
        ProgramDTO.AttributeDTO attributeDTO =
            new ProgramDTO.AttributeDTO().setAge(18).setSex(Sex.FEMALE).setMail("test@qq.com");
        ProgramDTO.GuestDTO guestDTO = new ProgramDTO.GuestDTO().setId(100L).setName("zsh").setAttribute(attributeDTO);
        ProgramDTO programDTO = new ProgramDTO().setId(200L).setFee(true).setName("running")
            .setGuestList(Collections.singletonList(guestDTO));
        Program program = ProgramDTO.to(programDTO);
        Program clone = ProgramDTO.clone(program);
        Program clone1 = SerializationUtils.clone(program);
        clone1.getGuestList().get(0).setName("xxxx");
        // System.out.println(clone);
        // System.out.println(clone1);
        // Guest@1102 Attribute@1105 Sex@1094 Integer@1106
        assertNotEquals("xxxx", program.getGuestList().get(0).getName());
        Guest guest = clone.getGuestList().get(0);
        guest.setName("xxxx");
        System.out.println();
        assertEquals("xxxx", program.getGuestList().get(0).getName());

    }

    public void test2() {
        ProgramDTO.AttributeDTO attributeDTO =
            new ProgramDTO.AttributeDTO().setAge(18).setSex(Sex.FEMALE).setMail("test@qq.com");
        final ProgramDTO.GuestDTO guestDTO =
            new ProgramDTO.GuestDTO().setId(100L).setName("zsh").setAttribute(attributeDTO);

        guestDTO.setName("lisi");

        System.out.println(guestDTO.getName());
    }
}
