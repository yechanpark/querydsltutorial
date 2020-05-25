package com.yechanpark.querydsltutorial.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "OTHERCUSTOMENTITY")
data class OtherCustomEntity(
    @Id @GeneratedValue
    var id: Long = 0,

    @Column(name = "DATETIMEINOTHER")
    var dateTimeInOther: LocalDateTime
)