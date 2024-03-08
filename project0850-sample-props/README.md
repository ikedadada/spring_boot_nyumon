== ハンズオン
=== 概要
外部化した設定値を読込んで、アプリケーションで使用します。@Value の手段を確認した後、@ConfigurationProperties の手段に変更します。

=== 手順
. SampleApplication クラスを実行してみましょう。コンソール画面に表示される「ディスカウントされた金額」は、DiscountServiceImpl が計算しています。

. DiscountServiceImpl クラスの中で、ディスカウント率とディスカウント上限は、外部化した設定値から取得しています。実際の値がどこで設定されているか分かりますか？

. 設定値を変えてみて、きちんと反映されるか確認してみましょう。

. @Value を使用した場合、プロパティ名の先頭文字が冗長になりがちです。@ConfigurationProperties を使用した方法に変えてみましょう。設定値を保持するクラスとして、DiscountProperties クラスを使用できます。
