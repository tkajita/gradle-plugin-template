import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender

import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.ERROR

// ログの出力フォーマット
def LOG_FORMAT = "%d{yyyy/MM/dd HH:mm:ss.SSS} %p [%t] %c:%M\\(%F:%L\\) %m%n"

// ログのデフォルト文字コード
def DEFAULT_CHARSET = Charset.forName("UTF-8")

// ログファイルへのログ出力設定
appender("FILE", RollingFileAppender) {
    file = "build/tomcat/logs/test.log"
    encoder(PatternLayoutEncoder) {
        charset = DEFAULT_CHARSET
        pattern = LOG_FORMAT
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = file + ".%d{yyyy-MM-dd}"
    }
}

// 標準出力へのログ出力設定
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = DEFAULT_CHARSET
        pattern = LOG_FORMAT
    }
}

// ログレベル設定
root(ERROR, ["FILE", "STDOUT"])
