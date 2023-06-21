import path from 'path';

import HtmlWebpackPlugin from 'html-webpack-plugin';
import {Configuration} from 'webpack';
import {Configuration as DevConfiguration} from 'webpack-dev-server';

const config: Configuration & DevConfiguration = {
    mode: 'production',
    devServer: {
        port: 4000
    },
    entry: {
        app: './entry/index.tsx'
    },
    target: 'web',
    resolve: {
        extensions: ['.tsx']
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: '/node_modules/'
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, 'entry', 'index.html'),
        })
    ]
};

// noinspection JSUnusedGlobalSymbols
export default config;
