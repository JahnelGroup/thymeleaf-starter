package com.jahnelgroup.domain.user

import com.jahnelgroup.config.loggerFor
import com.jahnelgroup.domain.user.group.GroupMember
import com.jahnelgroup.domain.user.group.GroupMemberRepo
import com.jahnelgroup.domain.user.group.GroupRepo
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import javax.transaction.Transactional

@Service
@Transactional
class UserService(
        private var userRepo: UserRepo,
        private var groupMemberRepo: GroupMemberRepo,
        private var groupRepo: GroupRepo,
        private var passwordEncoder: PasswordEncoder) {

    val logger = loggerFor(UserService::class.java)

    // TODO: only Admin should be able to call this
    fun createUser(user: User){
        user.password = passwordEncoder.encode(user.password)
        groupMemberRepo.save(GroupMember(username = user.username, group = groupRepo.findByGroupName("User").get()))
        logger.info("createUser: {}", user)
        userRepo.save(user)
    }

    fun updatePassword(username: String, plainTextPassword: String){
        var user = findByUsername(username)
        user.password = passwordEncoder.encode(plainTextPassword)
        userRepo.save(user)
        logger.info("user $username password was updated.")
    }

    fun updateProfile(username: String, firstName: String, lastName: String, email: String){
        var user = findByUsername(username)
        user.firstName = firstName
        user.lastName = lastName
        user.email = email
        userRepo.save(user)
        logger.info("user $username profile was updated.")
    }

    fun updatePreferences(username: String, preferences: String){
        //var user = findByUserName(username)
        //user.preferences = preferences
        //userRepo.save(user)
        logger.info("user $username preferences were updated.")
    }

    fun addUserToGroup(username: String, groupId: Long){
        var group = groupRepo.findById(groupId).get()
        groupMemberRepo.save(GroupMember(username = username, group = group))
        logger.info("user {} added to group {}", username, group.groupName)
    }

    fun removeUserFromGroup(username: String, groupId: Long){
        groupMemberRepo.deleteByUsernameAndGroupId(username, groupId)

        var group = groupRepo.findById(groupId).get()
        logger.info("user {} removed from group {}", username, group.groupName)
    }

    fun findByUsername(username: String) =
            userRepo.findByUsername(username).orElseThrow {
                RuntimeException("user by username '$username' does not exist");
            }
}