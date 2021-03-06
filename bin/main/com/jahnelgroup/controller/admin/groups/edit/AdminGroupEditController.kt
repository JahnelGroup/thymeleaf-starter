package com.jahnelgroup.controller.admin.groups.edit

import com.jahnelgroup.domain.user.UserService
import com.jahnelgroup.domain.user.group.GroupMemberJdbcRepo
import com.jahnelgroup.domain.user.group.GroupRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AdminGroupEditController(
        private var groupRepo: GroupRepo,
        private var groupMemberJdbcRepo: GroupMemberJdbcRepo,
        private var userService: UserService
){

    @GetMapping("/admin/groups/{groupId}")
    fun search(model: Model, @PathVariable groupId: Long, @RequestParam inputSearch: String?): String {
        model.addAttribute("group", groupRepo.findById(groupId).get())
        model.addAttribute("members", groupMemberJdbcRepo.searchMembers(groupId))

        // search users
        model.addAttribute("inputSearch", inputSearch)
        model.addAttribute("searchResults",
                if( inputSearch != null ) {
                    groupMemberJdbcRepo.searchNonMembers(groupId, inputSearch)
                }else {
                    groupMemberJdbcRepo.searchNonMembers(groupId)
                }
        )
        return "layouts/admin/groups/edit"
    }

    @GetMapping("/admin/groups/{groupId}/addMember")
    fun addMember(model: Model, @PathVariable groupId: Long, @RequestParam username: String): String{
        userService.addUserToGroup(username, groupId)

        model.addAttribute("group", groupRepo.findById(groupId).get())
        model.addAttribute("members", groupMemberJdbcRepo.searchMembers(groupId))

        // TODO: Should retain inputSearch
        model.addAttribute("searchResults", groupMemberJdbcRepo.searchNonMembers(groupId))

        return "layouts/admin/groups/edit"
    }

    @GetMapping("/admin/groups/{groupId}/removeMember")
    fun removeMember(model: Model, @PathVariable groupId: Long, @RequestParam username: String): String{
        userService.removeUserFromGroup(username, groupId)

        model.addAttribute("group", groupRepo.findById(groupId).get())
        model.addAttribute("members", groupMemberJdbcRepo.searchMembers(groupId))

        // TODO: Should retain inputSearch
        model.addAttribute("searchResults", groupMemberJdbcRepo.searchNonMembers(groupId))

        return "layouts/admin/groups/edit"
    }

}