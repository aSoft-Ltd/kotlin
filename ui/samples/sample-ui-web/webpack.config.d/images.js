config.module.rules.push({
    test: /\.(jpe?g|png|gif|svg)$/i,
    loader: "file-loader?name=app/images/[name].[ext]"
});

config.module.rules.push({
    test: /\.(woff(2)?|ttf|eot|svg)(\?v=\d+\.\d+\.\d+)?$/,
    use: [{
        loader: 'file-loader',
        options: {
            name: '[name].[ext]',
            outputPath: 'fonts/'
        }
    }]
});