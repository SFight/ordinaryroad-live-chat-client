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

package tech.ordinaryroad.live.chat.client.kuaishou.client;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.kuaishou.api.KuaishouApis;
import tech.ordinaryroad.live.chat.client.kuaishou.config.KuaishouLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.kuaishou.constant.KuaishouCmdEnum;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouConnectionListener;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouMsgListener;
import tech.ordinaryroad.live.chat.client.kuaishou.msg.base.IKuaishouMsg;
import tech.ordinaryroad.live.chat.client.kuaishou.netty.handler.KuaishouBinaryFrameHandler;
import tech.ordinaryroad.live.chat.client.kuaishou.netty.handler.KuaishouConnectionHandler;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author mjz
 * @date 2024/1/5
 */
@Slf4j
public class KuaishouLiveChatClient extends BaseNettyClient<
        KuaishouLiveChatClientConfig,
        KuaishouCmdEnum,
        IKuaishouMsg,
        IKuaishouMsgListener,
        KuaishouConnectionHandler,
        KuaishouBinaryFrameHandler> {

    @Getter
    KuaishouApis.RoomInitResult roomInitResult = new KuaishouApis.RoomInitResult();

    public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config, List<IKuaishouMsgListener> msgListeners, IKuaishouConnectionListener connectionListener, EventLoopGroup workerGroup) {
        super(config, workerGroup, connectionListener);
        addMsgListeners(msgListeners);

        // 初始化
        this.init();
    }

    public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config, IKuaishouMsgListener msgListener, IKuaishouConnectionListener connectionListener, EventLoopGroup workerGroup) {
        super(config, workerGroup, connectionListener);
        addMsgListener(msgListener);

        // 初始化
        this.init();
    }

    public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config, IKuaishouMsgListener msgListener, IKuaishouConnectionListener connectionListener) {
        this(config, msgListener, connectionListener, new NioEventLoopGroup());
    }

    public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config, IKuaishouMsgListener msgListener) {
        this(config, msgListener, null, new NioEventLoopGroup());
    }

    public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config) {
        this(config, null);
    }

    @Override
    public void init() {
        roomInitResult = KuaishouApis.roomInit(getConfig().getRoomId(), getConfig().getCookie());
        super.init();
    }

    @Override
    protected String getWebSocketUriString() {
        List<String> websocketUrls = roomInitResult.getWebsocketUrls();
        return CollUtil.get(websocketUrls, RandomUtil.randomInt(0, websocketUrls.size()));
    }

    @Override
    public KuaishouConnectionHandler initConnectionHandler(IBaseConnectionListener<KuaishouConnectionHandler> clientConnectionListener) {
        return new KuaishouConnectionHandler(
                WebSocketClientHandshakerFactory.newHandshaker(getWebsocketUri(), WebSocketVersion.V13, null, true, new DefaultHttpHeaders(), getConfig().getMaxFramePayloadLength()),
                KuaishouLiveChatClient.this, clientConnectionListener
        );
    }

    @Override
    public KuaishouBinaryFrameHandler initBinaryFrameHandler() {
        return new KuaishouBinaryFrameHandler(super.msgListeners, KuaishouLiveChatClient.this);
    }

    public void sendDanmu(Object danmu, Runnable success, Consumer<Throwable> failed) {
        super.sendDanmu(danmu, success, failed);
    }
}
