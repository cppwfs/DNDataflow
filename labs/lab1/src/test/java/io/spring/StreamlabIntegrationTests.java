package io.spring;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StreamlabIntegrationTests {

    @Autowired
    protected Processor channels;

    @Autowired
    protected MessageCollector collector;

    @Test
    public void contextLoads() {
        Assert.assertTrue(true);
    }

    /**
     * Checks whether the default properties load successfully.
     * <p>
     * Also, the test verifies whether the channel communication works, too.
     */
    public static class ChannelCommunicationIntegrationTests extends StreamlabIntegrationTests {

        @Test
        public void inAndOutTest() {
            channels.input().send(new GenericMessage<Object>(""));
            channels.input().send(new GenericMessage<>("foo"));
            channels.input().send(new GenericMessage<Object>("bar"));
            channels.input().send(new GenericMessage<Object>("foo meets bar"));
            channels.input().send(new GenericMessage<Object>("nothing but the best test"));
            assertThat(collector.forChannel(channels.output()), receivesPayloadThat(is("")));
            assertThat(collector.forChannel(channels.output()), receivesPayloadThat(is("FOO")));
            assertThat(collector.forChannel(channels.output()), receivesPayloadThat(is("BAR")));
            assertThat(collector.forChannel(channels.output()), receivesPayloadThat(is("FOO MEETS BAR")));
            assertThat(collector.forChannel(channels.output()), receivesPayloadThat(not("nothing but the best test")));
        }
    }
}
