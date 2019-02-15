package com.ssm.chapter10.aop.verifier.impl;

import com.ssm.chapter10.aop.verifier.RoleVerifier;
import com.ssm.chapter10.pojo.Role;

public class RoleVerifierImpl implements RoleVerifier {
    @Override
    public boolean verify(Role role) {
        return role != null;
    }
}
