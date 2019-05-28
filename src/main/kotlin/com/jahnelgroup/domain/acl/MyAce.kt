package com.jahnelgroup.domain.acl

import org.springframework.security.acls.model.Permission

class MyAce(
    var username: String,
    var permissions: Set<Permission>
)