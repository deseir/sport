/**
 * 初始化单笔代付详情对话框
 */
var ChannelInfoDlg = {
    channelInfoData : {},
    validateFields: {
    }
};

/**
 * 清除数据
 */
ChannelInfoDlg.clearData = function() {
    this.ChannelInfoDlg = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChannelInfoDlg.set = function(key, val) {
    this.channelInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChannelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ChannelInfoDlg.close = function() {
    parent.layer.close(window.parent.ChannelList.layerIndex);
}

/**
 * 收集数据
 */
ChannelInfoDlg.collectData = function() {
    this.set('uuid').set('id').set('channelName').set('city').set('fanyongRate')
        .set('accountCardno').set('joinMobile').set('address').set('accountName')
        .set('accountBank').set('joinPerson').set('bussName')
}

/**
 * 提交添加
 */
ChannelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    $('#ensure').prop('disabled',true);
    //提交信息
    $.ajax({
        type: "POST",
        url: '/cust/channelInfo/saveOrUpdate',
        dataType: 'json',
        data: this.channelInfoData,
        success: function(data) {
            var status=data.status;
            if(status=='0'){
                Feng.success("添加渠道信息成功!");
            }else{
                Feng.success("添加渠道信息失败!");
            }
            window.parent.ChannelList.table.refresh();
            ChannelInfoDlg.close();
        },
        error: function(data) {
            Feng.info("新增渠道信息失败！");
            window.parent.ChannelList.table.refresh();
            ChannelInfoDlg.close();
        },
        complete: function () {
            $('#ensure').prop('disabled',false);
        }
    });

};

/**
 * 提交修改
 */
ChannelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    $('#ensure').prop('disabled',true);
    //提交信息
    $.ajax({
        type: "POST",
        url: '/cust/channelInfo/saveOrUpdate',
        dataType: 'json',
        data: this.channelInfoData,
        success: function(data) {
            var status=data.status;
            if(status=='0'){
                Feng.success("修改渠道信息成功!");
            }else{
                Feng.success("修改渠道信息失败!");
            }
            window.parent.ChannelList.table.refresh();
            ChannelInfoDlg.close();
        },
        error: function(data) {
            Feng.info("修改渠道信息失败！");
            window.parent.ChannelList.table.refresh();
            ChannelInfoDlg.close();
        },
        complete: function () {
            $('#ensure').prop('disabled',false);
        }
    });
}

/**
 * 验证数据是否为空
 */
ChannelInfoDlg.validate = function () {
    $('#channelInfoForm').data("bootstrapValidator").resetForm();
    $('#channelInfoForm').bootstrapValidator('validate');
    return $("#channelInfoForm").data('bootstrapValidator').isValid();
}

$(function() {
    $('#uuid').val(Feng.uuidv4());
    Feng.initValidator("channelInfoForm", ChannelInfoDlg.validateFields);
});
