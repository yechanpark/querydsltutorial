package com.yechanpark.querydsltutorial.repository

import com.querydsl.sql.SQLQueryFactory
import com.yechanpark.querydsltutorial.entity.QCustomEntity
import com.yechanpark.querydsltutorial.entity.QOtherCustomEntity
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import javax.transaction.Transactional

@Repository
@Transactional
class QuerydslSQLRepository(private val sqlQueryFactory: SQLQueryFactory) {

    fun sqlQueryFactoryUnion(): MutableList<LocalDateTime>? {
        val entity = QCustomEntity.customEntity
        val otherEntity = QOtherCustomEntity.otherCustomEntity

        return sqlQueryFactory
            .select()
            .union(
                sqlQueryFactory.select(entity.dateTime.`as`("time")).from(entity),
                sqlQueryFactory.select(otherEntity.dateTimeInOther.`as`("time")).from(otherEntity)
            )
            .orderBy(entity.dateTime.`as`("time").desc())
            .fetch()
    }
}