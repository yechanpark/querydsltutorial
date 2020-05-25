package com.yechanpark.querydsltutorial.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "CUSTOMENTITY")
data class CustomEntity(
    @Id @GeneratedValue
    var id: Long = 0,

    @Column(name = "DATETIME")
    var dateTime: LocalDateTime
)