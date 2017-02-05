package io.spring;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StreamlabApplicationTests {

    @Test
    public void contextLoads() {
        Assert.assertTrue(true);
    }

    @Test
    public void testUppercase() {
        StreamlabApplication lab = new StreamlabApplication();

        Object payload = lab.uppercase("foo");

        assertTrue(payload != null);
        assertThat(payload.toString(), not(""));
        assertThat(payload.toString(), is("FOO"));
    }
}
