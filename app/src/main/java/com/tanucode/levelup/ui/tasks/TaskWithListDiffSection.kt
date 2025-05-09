package com.tanucode.levelup.ui.tasks

import androidx.recyclerview.widget.DiffUtil

class TaskWithListDiffSection : DiffUtil.ItemCallback<TaskSection>() {
    override fun areItemsTheSame(o: TaskSection, n: TaskSection): Boolean =
        (o is TaskSection.Header && n is TaskSection.Header && o.title ==n.title)
                || (o is TaskSection.Item && n is TaskSection.Item && o.data.task.id == n.data.task.id)

    override fun areContentsTheSame(
        o: TaskSection,
        n: TaskSection
    ): Boolean = o == n


}