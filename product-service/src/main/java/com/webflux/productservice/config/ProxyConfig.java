//package com.webflux.productservice.config;
//
//import de.flapdoodle.embed.mongo.config.Defaults;
//import de.flapdoodle.embed.mongo.packageresolver.Command;
//import de.flapdoodle.embed.process.config.RuntimeConfig;
////import de.flapdoodle.embed.process.config.process.ProcessOutput;
//import de.flapdoodle.embed.process.config.process.ProcessOutput;
//import de.flapdoodle.embed.process.config.store.DownloadConfig;
//import de.flapdoodle.embed.process.config.store.HttpProxyFactory;
//import de.flapdoodle.embed.process.io.Processors;
//import de.flapdoodle.embed.process.io.Slf4jLevel;
//import de.flapdoodle.embed.process.io.StreamProcessor;
//import de.flapdoodle.embed.process.io.progress.Slf4jProgressListener;
//import de.flapdoodle.embed.process.store.ExtractedArtifactStore;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.mongo.embedded.DownloadConfigBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.stream.Stream;
//
//@Configuration
//public class ProxyConfig {
//
//    @Value("${spring.mongodb.embedded.proxy.domain}")
//    private String proxyDomain;
//
//    @Value("${spring.mongodb.embedded.proxy.port}")
//    private Integer proxyPort;
//
//    @Bean
//    RuntimeConfig embeddedMongoRuntimeConfig(ObjectProvider<DownloadConfigBuilderCustomizer> downloadConfigBuilderCustomizers) {
//        Logger logger = LoggerFactory.getLogger(this.getClass().getPackage().getName() + ".EmbeddedMongo");
//        ProcessOutput processOutput = new ProcessOutput(Processors.logTo(logger, Slf4jLevel.INFO), Processors.logTo(logger, Slf4jLevel.ERROR), Processors.named("[console>]", Processors.logTo(logger, Slf4jLevel.DEBUG))) {
//            @Override
//            public StreamProcessor output() {
//                return null;
//            }
//
//            @Override
//            public StreamProcessor error() {
//                return null;
//            }
//
//            @Override
//            public StreamProcessor commands() {
//                return null;
//            }
//        };
//        return Defaults.runtimeConfigFor(Command.MongoD, logger).processOutput(processOutput).artifactStore(this.getArtifactStore(logger, downloadConfigBuilderCustomizers.orderedStream())).isDaemonProcess(false).build();
//    }
//
//    private ExtractedArtifactStore getArtifactStore(Logger logger, Stream<DownloadConfigBuilderCustomizer> downloadConfigBuilderCustomizers) {
//        de.flapdoodle.embed.process.config.store.ImmutableDownloadConfig.Builder downloadConfigBuilder = Defaults.downloadConfigFor(Command.MongoD);
//        downloadConfigBuilder.progressListener(new Slf4jProgressListener(logger));
//        downloadConfigBuilderCustomizers.forEach((customizer) -> {
//            customizer.customize(downloadConfigBuilder);
//        });
//        DownloadConfig downloadConfig = downloadConfigBuilder
//                .proxyFactory(new HttpProxyFactory(proxyDomain, proxyPort))  // <--- HERE
//                .build();
//        return Defaults.extractedArtifactStoreFor(Command.MongoD).withDownloadConfig(downloadConfig);
//    }
//}
