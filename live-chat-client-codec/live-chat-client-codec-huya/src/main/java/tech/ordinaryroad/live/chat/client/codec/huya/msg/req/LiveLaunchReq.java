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

package tech.ordinaryroad.live.chat.client.codec.huya.msg.req;

import com.qq.tars.protocol.tars.TarsInputStream;
import com.qq.tars.protocol.tars.TarsOutputStream;
import com.qq.tars.protocol.tars.TarsStructBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ordinaryroad.live.chat.client.codec.huya.msg.dto.LiveUserbase;
import tech.ordinaryroad.live.chat.client.codec.huya.msg.dto.UserId;

/**
 * @author mjz
 * @date 2023/10/5
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiveLaunchReq extends TarsStructBase {

    private UserId tId = new UserId();
    private LiveUserbase tLiveUB = new LiveUserbase();
    private boolean bSupportDomain;

    @Override
    public void writeTo(TarsOutputStream os) {
        os.write(this.tId, 0);
        os.write(this.tLiveUB, 1);
        os.write(this.bSupportDomain, 2);
    }

    @Override
    public void readFrom(TarsInputStream is) {
        this.tId = (UserId) is.directRead(this.tId, 0, false);
        this.tLiveUB = (LiveUserbase) is.directRead(this.tLiveUB, 1, false);
        this.bSupportDomain = is.read(this.bSupportDomain, 2, false);
    }

    @Override
    public TarsStructBase newInit() {
        return this;
    }
}
