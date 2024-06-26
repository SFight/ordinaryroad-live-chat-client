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

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.3
package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

public interface RspOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Rsp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 a = 1;</code>
   * @return The a.
   */
  int getA();

  /**
   * <code>int32 b = 2;</code>
   * @return The b.
   */
  int getB();

  /**
   * <code>int32 c = 3;</code>
   * @return The c.
   */
  int getC();

  /**
   * <code>string d = 4;</code>
   * @return The d.
   */
  String getD();
  /**
   * <code>string d = 4;</code>
   * @return The bytes for d.
   */
  com.google.protobuf.ByteString
      getDBytes();

  /**
   * <code>int32 e = 5;</code>
   * @return The e.
   */
  int getE();

  /**
   * <code>.Rsp.F f = 6;</code>
   * @return Whether the f field is set.
   */
  boolean hasF();
  /**
   * <code>.Rsp.F f = 6;</code>
   * @return The f.
   */
  Rsp.F getF();
  /**
   * <code>.Rsp.F f = 6;</code>
   */
  Rsp.FOrBuilder getFOrBuilder();

  /**
   * <code>string g = 7;</code>
   * @return The g.
   */
  String getG();
  /**
   * <code>string g = 7;</code>
   * @return The bytes for g.
   */
  com.google.protobuf.ByteString
      getGBytes();

  /**
   * <code>uint64 h = 10;</code>
   * @return The h.
   */
  long getH();

  /**
   * <code>uint64 i = 11;</code>
   * @return The i.
   */
  long getI();

  /**
   * <code>uint64 j = 13;</code>
   * @return The j.
   */
  long getJ();
}
