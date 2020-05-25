package com.yechanpark.querydsltutorial.repository

import com.yechanpark.querydsltutorial.entity.CustomEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomEntityRepository: JpaRepository<CustomEntity, Long>