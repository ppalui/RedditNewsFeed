package com.base.project.data.entity.RedditComment


data class ChildGroup(val children: List<Child>,
                      val after: String?,
                      val before: String?) {
}