/*
 * This file is a part of project QuickShop, the name is HttpCacheLoader.java
 *  Copyright (C) PotatoCraft Studio and contributors
 *
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the
 *  Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.maxgamer.quickshop.util;

import com.google.common.cache.CacheLoader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.nonquickshopstuff.com.sk89q.worldedit.util.net.HttpRequest;

public class HttpCacheLoader extends CacheLoader<URL, Optional<String>> {
  /**
   * Computes or retrieves the value corresponding to {@code key}.
   *
   * @param key the non-null key whose value should be loaded
   * @return the value associated with {@code key}; <b>must not be null</b>
   * @throws Exception            if unable to load the result
   * @throws InterruptedException if this method is interrupted. {@code
   *     InterruptedException} is
   *                              treated like any other {@code Exception} in
   * all respects except that, when it is caught, the thread's interrupt status
   * is set
   */
  @Override
  public Optional<String> load(@NotNull URL key) throws Exception {
    try {
      return Optional.ofNullable(HttpRequest.get(key)
                                     .execute()
                                     .expectResponseCode(200)
                                     .returnContent()
                                     .asString("UTF-8"));
    } catch (IOException e) {
      return Optional.empty();
    }
  }
}
