package teemoDevs.MainWebApplication.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class JpaH2Config(private val env: Environment? = null) {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("dataSource") dataSource: DataSource?
    ): LocalContainerEntityManagerFactoryBean {
        val em = builder
            .dataSource(dataSource)
            .packages("com.yechanpark.querydsltutorial.entity")
            .persistenceUnit("customer")
            .build()
        em.setJpaProperties(additionalJpaProperties())
        return em
    }

    @Bean
    fun transactionManager(@Qualifier("entityManagerFactory") entityManagerFactory: EntityManagerFactory?): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory!!)
    }

    private fun additionalJpaProperties(): Properties {
        val properties = Properties()

        // DDL 옵션 설정
        properties.setProperty("hibernate.hbm2ddl.auto", env!!.getProperty("spring.jpa.hibernate.ddl-auto"))

        // H2 Dialect 설정
        properties.setProperty("hibernate.dialect", env.getProperty("spring.datasource.db2.hibernate.dialect"))

        // SQL 출력
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"))

        // SQL 포매팅
        properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"))

        // SQL 코멘트 추가
        properties.setProperty(
            "hibernate.use_sql_comments",
            env.getProperty("spring.jpa.properties.hibernate.use_sql_comments")
        )
        return properties
    }

}