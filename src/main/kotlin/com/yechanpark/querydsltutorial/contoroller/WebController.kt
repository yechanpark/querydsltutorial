package com.yechanpark.querydsltutorial.contoroller

import com.yechanpark.querydsltutorial.service.QuerydslSQLService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class WebController(private val querydslSQLService: QuerydslSQLService) {

    @RequestMapping("/a")
    fun aFunc(): ResponseEntity<*> {
        return ResponseEntity(querydslSQLService.sqlQueryFactoryUnion(), HttpStatus.OK)
    }
}