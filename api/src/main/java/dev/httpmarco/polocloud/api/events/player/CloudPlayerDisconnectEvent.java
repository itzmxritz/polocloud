/*
 * Copyright 2024 Mirco Lindenau | HttpMarco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.httpmarco.polocloud.api.events.player;

import dev.httpmarco.osgan.networking.packet.PacketBuffer;
import dev.httpmarco.polocloud.api.packets.ComponentPacketHelper;
import dev.httpmarco.polocloud.api.player.CloudPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class CloudPlayerDisconnectEvent implements CloudPlayerEvent {

    private CloudPlayer cloudPlayer;

    @Override
    public void read(PacketBuffer buffer) {
        ComponentPacketHelper.writePlayer(this.cloudPlayer, buffer);
    }

    @Override
    public void write(PacketBuffer buffer) {
        this.cloudPlayer = ComponentPacketHelper.readPlayer(buffer);
    }
}
