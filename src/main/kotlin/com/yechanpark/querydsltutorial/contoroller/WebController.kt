package com.yechanpark.querydsltutorial.contoroller

import com.google.common.collect.ImmutableList
import com.querydsl.sql.Configuration
import com.querydsl.sql.H2Templates
import com.querydsl.sql.SQLQueryFactory
import com.querydsl.sql.SQLTemplates
import com.yechanpark.querydsltutorial.entity.QCustomEntity
import com.yechanpark.querydsltutorial.entity.QOtherCustomEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager
import javax.sql.DataSource


@RestController
@RequestMapping("/api")
class WebController(
    private val entityManager: EntityManager,
    private val dataSource: DataSource
) {
    @RequestMapping("/a")
    fun aFunc(): ResponseEntity<*> {
        val entity = QCustomEntity.customEntity
        val otherEntity = QOtherCustomEntity.otherCustomEntity


        val templates: SQLTemplates = H2Templates()
        val configuration = Configuration(templates)
        val queryFactory = SQLQueryFactory(configuration, dataSource)

        print(
            queryFactory
                .select()
                .union(
                    queryFactory.select(entity.dateTime.`as`("time")).from(entity),
                    queryFactory.select(otherEntity.dateTimeInOther.`as`("time")).from(otherEntity)
                )
                .orderBy(entity.dateTime.`as`("time").desc())
                .fetch()
        )
        return ResponseEntity<HttpStatus>(HttpStatus.OK)
    }
}