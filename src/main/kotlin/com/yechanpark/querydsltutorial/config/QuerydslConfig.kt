package com.yechanpark.querydsltutorial.config

import com.querydsl.sql.H2Templates
import com.querydsl.sql.SQLQueryFactory
import com.querydsl.sql.spring.SpringConnectionProvider
import com.querydsl.sql.spring.SpringExceptionTranslator
import com.querydsl.sql.types.DateTimeType
import com.querydsl.sql.types.LocalDateType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.Connection
import javax.inject.Provider
import javax.sql.DataSource

@Configuration
class QuerydslConfig(private val dataSource: DataSource) {

    @Bean
    fun querydslConfiguration(): com.querydsl.sql.Configuration? {
        val templates = H2Templates.builder().build()
        val configuration = com.querydsl.sql.Configuration(templates)
        configuration.setExceptionTranslator(SpringExceptionTranslator())
        configuration.register(DateTimeType())
        configuration.register(LocalDateType())
        return configuration
    }

    @Bean
    fun queryFactory(): SQLQueryFactory? {
        val provider: Provider<Connection> = SpringConnectionProvider(dataSource)
        return SQLQueryFactory(querydslConfiguration(), provider)
    }
}