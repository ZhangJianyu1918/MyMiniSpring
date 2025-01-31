package org.spring.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Test;
import org.spring.core.io.DefaultResourceLoader;
import org.spring.core.io.FileSystemResource;
import org.spring.core.io.Resource;
import org.spring.core.io.UrlResource;

import java.io.InputStream;
import static org.assertj.core.api.Assertions.assertThat;


public class ResourceAndResourceLoaderTest {
    @Test
    public void testResourceLoader() throws Exception {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        // 加载classpath下的资源
        Resource classPath = defaultResourceLoader.getResource("classpath:hello.txt");
        InputStream classPathInputStream = classPath.getInputStream();
        String classPathContent = IoUtil.readUtf8(classPathInputStream);
        System.out.println("Class Path: " + classPathContent);
        assertThat(classPathContent).isEqualTo("hello world");

        // 加载文件系统资源
        Resource systemFile = defaultResourceLoader.getResource("src/test/resources/hello.txt");
        assertThat(systemFile).isInstanceOf(FileSystemResource.class);
        InputStream systemFileInputStream = systemFile.getInputStream();
        String systemFileContent = IoUtil.readUtf8(systemFileInputStream);
        System.out.println("File System " + systemFileContent);
        assertThat(systemFileContent).isEqualTo("hello world");

        // 加载url资源
        Resource urlResource = defaultResourceLoader.getResource("https://www.baidu.com/");
        assertThat(urlResource).isInstanceOf(UrlResource.class);
        InputStream urlResourceInputStream = urlResource.getInputStream();
        String urlContent = IoUtil.readUtf8(urlResourceInputStream);
        System.out.println(urlContent);
    }
}
