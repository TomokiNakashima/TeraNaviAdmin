/*!
 * FileInput Chinese Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 * @author kangqf <kangqingfei@gmail.com>
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";

    $.fn.fileinput.locales.zh = {
        fileSingle: 'ファイル',
        filePlural: '複数ファイル',
        browseLabel: '選択 &hellip;',
        removeLabel: '削除',
        removeTitle: 'クリア',
        cancelLabel: '取り消し',
        cancelTitle: 'アップロード中止',
        uploadLabel: 'アップロード',
        uploadTitle: 'ファイルをアップロード',
        msgSizeTooLarge: 'ファイル "{name}" (<b>{size} KB</b>) 容量オーバー <b>{maxSize} KB</b>. もう一回やり直してください!',
        msgFilesTooLess: 'ファイル <b>{n}</b> {files} を選択してください!',
        msgFilesTooMany: 'アップロードするファイル数 <b>({n})</b> 制限を越えています <b>{m}</b>. もう一回やり直してください!',
        msgFileNotFound: 'ファイル "{name}" 未発見!',
        msgFileSecured: 'セキュリティ制限，読まれないため "{name}".',
        msgFileNotReadable: 'ファイル "{name}" 読み込み不可.',
        msgFilePreviewAborted: ' "{name}" のプレビューをキャンセル.',
        msgFilePreviewError: ' "{name}" の読み込み時にエラーがありました.',
        msgInvalidFileType: '処理できないファイルです "{name}".  "{types}" のファイルを選択してください.',
        msgInvalidFileExtension: '拡張子が正しくない "{name}".  "{extensions}" を選択してください.',
        msgValidationError: 'ファイルアップロードエラー',
        msgLoading: ' {index} 個目のファイルをアップロード中 全部 {files} &hellip;',
        msgProgress: ' {index} 個目のファイルをアップロード中 全部 {files} - {name} - {percent}% 完成.',
        msgSelected: '{n} {files} 選択された',
        msgFoldersNotAllowed: 'ドラッグのみサーポートしています!  {n} 個ファイルを無視しました.',
        dropZoneTitle: 'ここにドラッグしてください &hellip;',
        slugCallback: function(text) {
            return text ? text.split(/(\\|\/)/g).pop().replace(/[^\w\u4e00-\u9fa5\-.\\\/ ]+/g, '') : '';
        }
    };

    $.extend($.fn.fileinput.defaults, $.fn.fileinput.locales.zh);
})(window.jQuery);
