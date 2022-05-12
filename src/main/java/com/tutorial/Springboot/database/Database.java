package com.tutorial.Springboot.database;


import com.tutorial.Springboot.repositories.ConditionRepository;
import com.tutorial.Springboot.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Now connect with mysql using JPA
/*
docker run -d --rm --name mysql-spring-boot-tutorial mysql:latest \
docker run -e MYSQL_ROOT_PASSWORD=123456  mysql:latest\
docker run -e MYSQL_USER=root mysql:latest\
docker run -e MYSQL_PASSWORD=123456 mysql:latest \
docker run -e MYSQL_DATABASE=test mysql:latest\
docker run -p 3309:3306 mysql:latest\
docker run --volume mysql-spring-boot-tutorial-volume:/var/lib/mysql  mysql:latest\
mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u root -p

* */

@Configuration
public class Database {
    //LOGGER
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase (ProductRepository productRepository){

        return  new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//               // ProductDAO productDAO = new ProductDAO();
//                Product productA = new Product("Macbook",2022, 2600.0,"" );
//                Product productB = new Product("Ipad",2021, 2000.0,"" );
//                logger.info("insert data: " + productRepository.save(productA));
//                //productDAO.addProduct(productA);
//                logger.info("insert data: " + productRepository.save(productB));
//                //productDAO.addProduct(productB);
            }
        };
    }

    @Bean
    CommandLineRunner initDatabase1 (ConditionRepository conditionRepository){

        return  new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Condition condition1 = new Condition("Level 1",1200,3,7,90);
//                Condition condition2 = new Condition("Level 2",1200,3,10,90);
//                Condition condition3 = new Condition("Level 3",1200,2,10,95);
//                Condition condition4 = new Condition("Level 4",1200,2,10,95);
//                Condition condition5 = new Condition("Level 5",1200,2,10,95);
//                logger.info("insert data: " + conditionRepository.save(condition1));
//                logger.info("insert data: " + conditionRepository.save(condition2));
//                logger.info("insert data: " + conditionRepository.save(condition3));
//                logger.info("insert data: " + conditionRepository.save(condition4));
//                logger.info("insert data: " + conditionRepository.save(condition5));

            }
        };
    }

   /* @Bean
    CommandLineRunner initDatabaseSalary (SalaryScaleRepository salaryScaleRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                SalaryScale salaryScale1 = new SalaryScale(1l,"Level 1", 500.0);
                SalaryScale salaryScale2 = new SalaryScale(2l,"Level 2", 700.0);
                SalaryScale salaryScale3 = new SalaryScale(3l,"Level 3", 1100.0);
                SalaryScale salaryScale4 = new SalaryScale(4l,"Level 4", 1500.0);
                SalaryScale salaryScale5 = new SalaryScale(5l,"Level 5", 2500.0);
                logger.info("insert data: " + salaryScaleRepository.save(salaryScale1));
                logger.info("insert data: " + salaryScaleRepository.save(salaryScale2));
                logger.info("insert data: " + salaryScaleRepository.save(salaryScale3));
                logger.info("insert data: " + salaryScaleRepository.save(salaryScale4));
                logger.info("insert data: " + salaryScaleRepository.save(salaryScale5));

            }
        };
    }*/


}
