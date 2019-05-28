package com.jahnelgroup.domain.task

import com.jahnelgroup.domain.acl.MyAce
import com.jahnelgroup.domain.acl.MyAclService
import com.jahnelgroup.domain.context.UserContextService
import org.springframework.security.acls.domain.BasePermission
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class TaskService(
        var myAclService: MyAclService,
        var userContextService: UserContextService,
        var taskListRepo: TaskListRepo
){

    fun findAccessibleTaskLists(): List<TaskList> {
        val objectsWithAccess = myAclService.getObjectsWithAccess(TaskList::class.java, userContextService.currentUsername()!!)
        return taskListRepo.findAllById(objectsWithAccess!!.map { it.identifier as Long })
    }

    fun findSharedTaskLists(): List<TaskList> {
        val sharedObjects = myAclService.getObjectsWithAccessByOtherUsers(TaskList::class.java, userContextService.currentUsername()!!)
        return taskListRepo.findAllById(sharedObjects!!.map { it.identifier as Long })
    }

    fun addUserToTaskList(taskListId: Long, username: String) =
        myAclService.upsertAces(TaskList::class, taskListId,
                MyAce(username, setOf(BasePermission.READ, BasePermission.WRITE)))

}