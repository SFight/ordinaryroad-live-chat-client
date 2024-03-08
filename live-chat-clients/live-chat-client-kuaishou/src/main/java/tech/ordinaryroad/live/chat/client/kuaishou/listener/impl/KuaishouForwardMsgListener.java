/*
 * MIT License
 *
 * Copyright (c) 2023 OrdinaryRoad
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tech.ordinaryroad.live.chat.client.kuaishou.listener.impl;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouMsgListener;
import tech.ordinaryroad.live.chat.client.kuaishou.msg.KuaishouDanmuMsg;
import tech.ordinaryroad.live.chat.client.kuaishou.msg.KuaishouGiftMsg;
import tech.ordinaryroad.live.chat.client.kuaishou.msg.KuaishouLikeMsg;
import tech.ordinaryroad.live.chat.client.websocket.client.WebSocketLiveChatClient;
import tech.ordinaryroad.live.chat.client.websocket.config.WebSocketLiveChatClientConfig;

import java.nio.charset.StandardCharsets;

/**
 * @author mjz
 * @date 2024/3/8
 */
public class KuaishouForwardMsgListener implements IKuaishouMsgListener {

    private WebSocketLiveChatClient webSocketLiveChatClient;

    public KuaishouForwardMsgListener(String webSocketUri) {
        if (StrUtil.isNotBlank(webSocketUri)) {
            webSocketLiveChatClient = new WebSocketLiveChatClient(
                    WebSocketLiveChatClientConfig.builder()
                            .websocketUri(webSocketUri)
                            .build()
            );
            webSocketLiveChatClient.connect();
        }
    }

    @Override
    public void onDanmuMsg(KuaishouDanmuMsg msg) {
        forward(msg);
    }

    @Override
    public void onGiftMsg(KuaishouGiftMsg msg) {
        forward(msg);
    }

    @Override
    public void onLikeMsg(KuaishouLikeMsg msg) {
        forward(msg);
    }

    private void forward(IMsg msg) {
        if (webSocketLiveChatClient == null) {
            return;
        }
        webSocketLiveChatClient.send(new BinaryWebSocketFrame(Unpooled.wrappedBuffer(msg.toString().getBytes(StandardCharsets.UTF_8))));
    }
}
