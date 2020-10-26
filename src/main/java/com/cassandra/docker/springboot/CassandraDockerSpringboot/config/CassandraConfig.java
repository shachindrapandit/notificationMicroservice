package com.cassandra.docker.springboot.CassandraDockerSpringboot.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

        private static final Log LOGGER = LogFactory.getLog(CassandraConfig.class);

        @Value("${spring.data.cassandra.contact-points:placeholder}")
        private String contactPoints;
        @Value("${spring.data.cassandra.port}")
        private int port;
        @Value("${spring.data.cassandra.keyspace-name:placeholder}")
        private String keyspace;
        @Value("${spring.data.cassandra.schema-action}")
        private String schemaAction;
        @Value("${spring.data.cassandra.local-datacenter}")
        private String localDatacenter;

        @Override
        protected String getKeyspaceName() {

                return keyspace;
        }
        @Override
        protected String getContactPoints()
        {
                return contactPoints;
        }
        @Override
        protected int getPort()
        {
                return port;
        }
        @Override
        public SchemaAction getSchemaAction()
        {
                return SchemaAction.CREATE_IF_NOT_EXISTS;
        }
        @Override
        public String[] getEntityBasePackages()
        {
                return new String[]{"com.company.domain.data"};
        }

        @Override
        public CassandraClusterFactoryBean cluster() {
           //     PlainTextAuthProvider authProvider = new PlainTextAuthProvider(userName, password);

                CassandraClusterFactoryBean cluster=new CassandraClusterFactoryBean();

                cluster.setJmxReportingEnabled(false);
                cluster.setContactPoints(contactPoints);
                cluster.setPort(port);
          //      cluster.setAuthProvider(authProvider);
                cluster.setKeyspaceCreations(getKeyspaceCreations());
          //      cluster.setReconnectionPolicy(new ConstantReconnectionPolicy(1000));

                return cluster;
        }


        @Override
        protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

                CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keyspace)
                        .ifNotExists()
                        .with(KeyspaceOption.DURABLE_WRITES, true);

                return Arrays.asList(specification);
        }



        @Override
        protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
                return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(keyspace));
        }

}
