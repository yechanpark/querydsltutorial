package com.yechanpark.querydsltutorial.service

import com.yechanpark.querydsltutorial.repository.QuerydslSQLRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class QuerydslSQLService (private val querydslSQLRepository: QuerydslSQLRepository) {
    fun sqlQueryFactoryUnion(): MutableList<LocalDateTime>? {
        return querydslSQLRepository.sqlQueryFactoryUnion()
    }
}