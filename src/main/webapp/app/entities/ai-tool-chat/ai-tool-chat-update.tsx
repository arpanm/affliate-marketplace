import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getAiToolSessions } from 'app/entities/ai-tool-session/ai-tool-session.reducer';
import { ChatType } from 'app/shared/model/enumerations/chat-type.model';
import { createEntity, getEntity, reset, updateEntity } from './ai-tool-chat.reducer';

export const AiToolChatUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const aiToolSessions = useAppSelector(state => state.aiToolSession.entities);
  const aiToolChatEntity = useAppSelector(state => state.aiToolChat.entity);
  const loading = useAppSelector(state => state.aiToolChat.loading);
  const updating = useAppSelector(state => state.aiToolChat.updating);
  const updateSuccess = useAppSelector(state => state.aiToolChat.updateSuccess);
  const chatTypeValues = Object.keys(ChatType);

  const handleClose = () => {
    navigate('/ai-tool-chat');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAiToolSessions({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...aiToolChatEntity,
      ...values,
      session: aiToolSessions.find(it => it.id.toString() === values.session?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          type: 'UserMessage',
          ...aiToolChatEntity,
          session: aiToolChatEntity?.session?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.aiToolChat.home.createOrEditLabel" data-cy="AiToolChatCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.aiToolChat.home.createOrEditLabel">Create or edit a AiToolChat</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="ai-tool-chat-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.message')}
                id="ai-tool-chat-message"
                name="message"
                data-cy="message"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.videoUrl')}
                id="ai-tool-chat-videoUrl"
                name="videoUrl"
                data-cy="videoUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.paymentUrl')}
                id="ai-tool-chat-paymentUrl"
                name="paymentUrl"
                data-cy="paymentUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.type')}
                id="ai-tool-chat-type"
                name="type"
                data-cy="type"
                type="select"
              >
                {chatTypeValues.map(chatType => (
                  <option value={chatType} key={chatType}>
                    {translate(`affliateMarketplaceApp.ChatType.${chatType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.isFinalVideo')}
                id="ai-tool-chat-isFinalVideo"
                name="isFinalVideo"
                data-cy="isFinalVideo"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.isDownloaded')}
                id="ai-tool-chat-isDownloaded"
                name="isDownloaded"
                data-cy="isDownloaded"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.isActive')}
                id="ai-tool-chat-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.createdBy')}
                id="ai-tool-chat-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.createdOn')}
                id="ai-tool-chat-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.updatedBy')}
                id="ai-tool-chat-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolChat.updatedOn')}
                id="ai-tool-chat-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="ai-tool-chat-session"
                name="session"
                data-cy="session"
                label={translate('affliateMarketplaceApp.aiToolChat.session')}
                type="select"
              >
                <option value="" key="0" />
                {aiToolSessions
                  ? aiToolSessions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/ai-tool-chat" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default AiToolChatUpdate;
