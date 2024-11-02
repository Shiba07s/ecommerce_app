1. Start Zipkin Server
<pre><code>docker run -d -p 9411:9411 openzipkin/zipkin</code></pre>
<button class="copy-button" data-clipboard-text="docker run -d -p 9411:9411 openzipkin/zipkin">Copy</button>
Starts the Zipkin server.

<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.8/clipboard.min.js"></script>
<script>
new ClipboardJS('.copy-button');
</script>
<style>
.copy-button {
    margin-left: 10px;
    padding: 5px 10px;
    background-color: #f1f1f1;
    border: 1px solid #ccc;
    border-radius: 3px;
    cursor: pointer;
}
.copy-button:hover {
    background-color: #e1e1e1;
}
pre {
    background-color: #f4f4f4;
    border: 1px solid #ddd;
    border-left: 3px solid #f36d33;
    color: #666;
    page-break-inside: avoid;
    font-family: monospace;
    font-size: 15px;
    line-height: 1.6;
    margin-bottom: 1.6em;
    max-width: 100%;
    overflow: auto;
    padding: 1em 1.5em;
    display: block;
    word-wrap: break-word;
}
</style>
