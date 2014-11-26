/*
 * Copyright 2014 Alex Kraynov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.rollercage;

import com.github.cage.Cage;
import com.github.cage.GCage;
import com.github.cage.YCage;
import org.apache.commons.codec.binary.Base64;
import org.apache.roller.weblogger.config.WebloggerConfig;
import org.apache.roller.weblogger.ui.rendering.plugins.comments.CommentAuthenticator;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CageCommentAuthenticator implements CommentAuthenticator {

    private final static String TOKEN = "token";
    private final static String ANSWER = "answer";
    private final Cage cage;

    {
        if ("y" == WebloggerConfig.getProperty("comment.authenticator.type")
                || "Y" == WebloggerConfig.getProperty("comment.authenticator.type")) {
            cage = new YCage();
        } else {
            cage = new GCage();
        }
    }

    @Override
    public String getHtml(javax.servlet.http.HttpServletRequest req) {
        String token = cage.getTokenGenerator().next();
        HttpSession session = req.getSession(true);
        session.setAttribute(TOKEN, token);
        StringBuilder result = new StringBuilder();
        result.append("<p>Please solve the puzzle: </p>");
        result.append("<p>");
        result.append("<img alt=\"CAPTCHA\" src=\"data:image/jpeg;base64,");
        result.append(Base64.encodeBase64String(cage.draw(token)));
        result.append("\"/>");

        result.append("&nbsp;<input name=\"" + ANSWER + "\"/>");
        result.append("</p>");
        return result.toString();
    }

    @Override
    public boolean authenticate(javax.servlet.http.HttpServletRequest req) {
        boolean authentic = false;

        HttpSession session = req.getSession(false);
        String answer = req.getParameter(ANSWER);

        if (answer != null && session != null
                && answer.equals(session.getAttribute(TOKEN))) {
            authentic = true;
            session.removeAttribute(TOKEN);
        }

        return authentic;
    }

}
